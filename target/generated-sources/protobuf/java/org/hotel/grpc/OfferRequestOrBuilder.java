// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: HotelService.proto

package org.hotel.grpc;

public interface OfferRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:OfferRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 agencyId = 1;</code>
   */
  long getAgencyId();

  /**
   * <code>string agencyPassword = 2;</code>
   */
  java.lang.String getAgencyPassword();
  /**
   * <code>string agencyPassword = 2;</code>
   */
  com.google.protobuf.ByteString
      getAgencyPasswordBytes();

  /**
   * <code>string fromDate = 3;</code>
   */
  java.lang.String getFromDate();
  /**
   * <code>string fromDate = 3;</code>
   */
  com.google.protobuf.ByteString
      getFromDateBytes();

  /**
   * <code>string toDate = 4;</code>
   */
  java.lang.String getToDate();
  /**
   * <code>string toDate = 4;</code>
   */
  com.google.protobuf.ByteString
      getToDateBytes();

  /**
   * <code>int32 lodgedPeopleCount = 5;</code>
   */
  int getLodgedPeopleCount();
}