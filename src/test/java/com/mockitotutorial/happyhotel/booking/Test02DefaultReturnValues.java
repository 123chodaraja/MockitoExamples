package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class Test02DefaultReturnValues {

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
        System.out.println("list returned: "+roomServiceMock.getAvailableRooms());
        System.out.println("room available " + roomServiceMock.findAvailableRoomId(null));
        System.out.println("primitive return: "+ roomServiceMock.getRoomCount());
    }
    @Test
    void shouldCountAvailablePlaces() {
        //given
        int expected=0;
        //when
        int actual = bookingService.getAvailablePlaceCount();
        //then
        assertEquals(expected,actual);

    }
}
