package org.hotel.grpc;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: HotelService.proto")
public final class HotelServiceGrpc {

  private HotelServiceGrpc() {}

  public static final String SERVICE_NAME = "HotelService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<org.hotel.grpc.OfferRequest,
      org.hotel.grpc.OfferResponse> METHOD_FIND_AVAILABLE_OFFERS =
      io.grpc.MethodDescriptor.<org.hotel.grpc.OfferRequest, org.hotel.grpc.OfferResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "HotelService", "findAvailableOffers"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              org.hotel.grpc.OfferRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              org.hotel.grpc.OfferResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<org.hotel.grpc.BookingRequest,
      org.hotel.grpc.BookingResponse> METHOD_CREATE_BOOKING =
      io.grpc.MethodDescriptor.<org.hotel.grpc.BookingRequest, org.hotel.grpc.BookingResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "HotelService", "createBooking"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              org.hotel.grpc.BookingRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              org.hotel.grpc.BookingResponse.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static HotelServiceStub newStub(io.grpc.Channel channel) {
    return new HotelServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static HotelServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new HotelServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static HotelServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new HotelServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class HotelServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void findAvailableOffers(org.hotel.grpc.OfferRequest request,
        io.grpc.stub.StreamObserver<org.hotel.grpc.OfferResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_FIND_AVAILABLE_OFFERS, responseObserver);
    }

    /**
     */
    public void createBooking(org.hotel.grpc.BookingRequest request,
        io.grpc.stub.StreamObserver<org.hotel.grpc.BookingResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CREATE_BOOKING, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_FIND_AVAILABLE_OFFERS,
            asyncUnaryCall(
              new MethodHandlers<
                org.hotel.grpc.OfferRequest,
                org.hotel.grpc.OfferResponse>(
                  this, METHODID_FIND_AVAILABLE_OFFERS)))
          .addMethod(
            METHOD_CREATE_BOOKING,
            asyncUnaryCall(
              new MethodHandlers<
                org.hotel.grpc.BookingRequest,
                org.hotel.grpc.BookingResponse>(
                  this, METHODID_CREATE_BOOKING)))
          .build();
    }
  }

  /**
   */
  public static final class HotelServiceStub extends io.grpc.stub.AbstractStub<HotelServiceStub> {
    private HotelServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HotelServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HotelServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HotelServiceStub(channel, callOptions);
    }

    /**
     */
    public void findAvailableOffers(org.hotel.grpc.OfferRequest request,
        io.grpc.stub.StreamObserver<org.hotel.grpc.OfferResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_FIND_AVAILABLE_OFFERS, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createBooking(org.hotel.grpc.BookingRequest request,
        io.grpc.stub.StreamObserver<org.hotel.grpc.BookingResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CREATE_BOOKING, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class HotelServiceBlockingStub extends io.grpc.stub.AbstractStub<HotelServiceBlockingStub> {
    private HotelServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HotelServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HotelServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HotelServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.hotel.grpc.OfferResponse findAvailableOffers(org.hotel.grpc.OfferRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_FIND_AVAILABLE_OFFERS, getCallOptions(), request);
    }

    /**
     */
    public org.hotel.grpc.BookingResponse createBooking(org.hotel.grpc.BookingRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CREATE_BOOKING, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class HotelServiceFutureStub extends io.grpc.stub.AbstractStub<HotelServiceFutureStub> {
    private HotelServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HotelServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HotelServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HotelServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.hotel.grpc.OfferResponse> findAvailableOffers(
        org.hotel.grpc.OfferRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_FIND_AVAILABLE_OFFERS, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.hotel.grpc.BookingResponse> createBooking(
        org.hotel.grpc.BookingRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CREATE_BOOKING, getCallOptions()), request);
    }
  }

  private static final int METHODID_FIND_AVAILABLE_OFFERS = 0;
  private static final int METHODID_CREATE_BOOKING = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final HotelServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(HotelServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_FIND_AVAILABLE_OFFERS:
          serviceImpl.findAvailableOffers((org.hotel.grpc.OfferRequest) request,
              (io.grpc.stub.StreamObserver<org.hotel.grpc.OfferResponse>) responseObserver);
          break;
        case METHODID_CREATE_BOOKING:
          serviceImpl.createBooking((org.hotel.grpc.BookingRequest) request,
              (io.grpc.stub.StreamObserver<org.hotel.grpc.BookingResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class HotelServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.hotel.grpc.HotelServiceOuterClass.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (HotelServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new HotelServiceDescriptorSupplier())
              .addMethod(METHOD_FIND_AVAILABLE_OFFERS)
              .addMethod(METHOD_CREATE_BOOKING)
              .build();
        }
      }
    }
    return result;
  }
}
