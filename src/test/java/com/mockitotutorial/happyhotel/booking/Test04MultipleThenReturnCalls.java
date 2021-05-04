package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Test04MultipleThenReturnCalls {

    private BookingService bookingService;
    private  PaymentService paymentServiceMock;
    private  RoomService roomServiceMock;
    private  BookingDAO bookingDAOMock;
    private  MailSender mailSenderMock;

    @BeforeEach
    void setup() {
        this.paymentServiceMock = mock(PaymentService.class);
        this.roomServiceMock = mock(RoomService.class);
        this.bookingDAOMock = mock(BookingDAO.class);
        this.mailSenderMock = mock(MailSender.class);
        this.bookingService = new BookingService(paymentServiceMock,roomServiceMock,bookingDAOMock,mailSenderMock);
    }
    @Test
    void shouldCountAvailablePlaces_whenCalledMultipleTimes() {
        //given
        when(this.roomServiceMock.getAvailableRooms())
                .thenReturn(Collections.singletonList(new Room("1",5)))
                .thenReturn(Collections.emptyList());
        int expectedFirstTime = 5;
        int expectedSecondTime = 0;

        //when
        int actualFirstTie = bookingService.getAvailablePlaceCount();
        int actualSecondTime = bookingService.getAvailablePlaceCount();

        //then
       assertAll(
               ()->assertEquals(expectedFirstTime,actualFirstTie),
               ()->assertEquals(expectedSecondTime,actualSecondTime)
       );
    }

}
