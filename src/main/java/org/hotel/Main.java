package org.hotel;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.hotel.dao.PartnershipDAO;
import org.hotel.injector.MainInjector;
import org.hotel.ws.HotelServiceImpl;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {

        Injector injector = Guice.createInjector(new MainInjector());

        HotelServiceImpl hotelService = injector.getInstance(HotelServiceImpl.class);

        Server server = ServerBuilder
                .forPort(8080)
                .addService(hotelService)
                .build();

        server.start();
        System.err.println("grpc server is running in http://localhost:8080");

        server.awaitTermination();
    }
}