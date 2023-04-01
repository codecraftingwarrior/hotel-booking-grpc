package org.hotel.ws;

import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.stub.StreamObserver;
import org.hotel.dao.AgencyDAO;
import org.hotel.dao.BookingDAO;
import org.hotel.dao.OfferDAO;
import org.hotel.dao.PartnershipDAO;
import org.hotel.entity.Offer;
import org.hotel.enums.OfferState;
import org.hotel.grpc.*;
import org.hotel.utils.DateParser;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class HotelServiceImpl extends HotelServiceGrpc.HotelServiceImplBase {
    private final Long CURRENT_HOTEL_ID = 1L;
    @Inject
    private PartnershipDAO partnershipDAO;
    @Inject
    private AgencyDAO agencyDAO;
    @Inject
    private OfferDAO offerDAO;
    @Inject
    private BookingDAO bookingDAO;

    public HotelServiceImpl() {

    }


    @Override
    public void findAvailableOffers(OfferRequest request, StreamObserver<OfferResponse> responseObserver) {

        if (!this.partnershipDAO.arePartners(request.getAgencyId(), this.CURRENT_HOTEL_ID))
            triggerError(responseObserver, "Vous n'êtes pas partenaire avec cet hôtel.");


        if (!this.agencyDAO.isPasswordValid(request.getAgencyId(), request.getAgencyPassword()))
            triggerError(responseObserver, "Authentification echoué identifiant ou mot de passe incorrect.");

        try {

            Date parsedFromDate = DateParser.parse(request.getFromDate()), parsedToDate = DateParser.parse(request.getToDate());
            List<OfferItem> offerItems = this
                    .offerDAO
                    .findAvailableOffers(this.CURRENT_HOTEL_ID, request.getAgencyId(), request.getLodgedPeopleCount(), parsedFromDate, parsedToDate);

            OfferResponse response = OfferResponse
                    .newBuilder()
                    .addAllOffers(offerItems)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            triggerError(responseObserver, e.getMessage());
            e.printStackTrace();
        }


    }

    @Override
    public void createBooking(BookingRequest request, StreamObserver<BookingResponse> responseObserver) {

        if (!this.partnershipDAO.arePartners(request.getAgencyId(), this.CURRENT_HOTEL_ID))
            triggerError(responseObserver, "Vous n'êtes pas partenaire avec cet hôtel.");

        if (!this.agencyDAO.isPasswordValid(request.getAgencyId(), request.getLogin(), request.getPassword()))
            triggerError(responseObserver, "Authentification echoué identifiant ou mot de passe incorrect.");

        if (!this.offerDAO.exists(request.getOfferId(), request.getAgencyId()))
            triggerError(responseObserver, "Cette offre est introuvable.");

        Offer offer = this.offerDAO.findById(request.getOfferId());

        if (offer.getState().equals(OfferState.CONFIRMED))
            triggerError(responseObserver, "Cette offre est déjà confirmée.");

        Map<String, String> bookingResult = this.bookingDAO.createFromOffer(offer, request.getClient());

        BookingResponse response = BookingResponse
                .newBuilder()
                .setReference(bookingResult.get("reference"))
                .setClientFullName(bookingResult.get("clientFullname"))
                .setFromDate(bookingResult.get("fromDate"))
                .setToDate(bookingResult.get("toDate"))
                .build();

        responseObserver.onNext(response);

        responseObserver.onCompleted();
    }

    private <T> void triggerError(StreamObserver<T> responseObserver, String message) {
        Metadata.Key<ErrorResponse> errorResponseKey = ProtoUtils
                .keyForProto(ErrorResponse.getDefaultInstance());

        ErrorResponse errorResponse = ErrorResponse
                .newBuilder()
                .setMessage(message)
                .build();

        Metadata metadata = new Metadata();
        metadata.put(errorResponseKey, errorResponse);

        responseObserver
                .onError(Status.INVALID_ARGUMENT
                        .withDescription(message)
                        .asRuntimeException(metadata));
    }
}
