package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Test03customValues {

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
    void shouldCountAvailablePlaces_whenOneRoomis_Available() {
        //given
        when(this.roomServiceMock.getAvailableRooms()).thenReturn(Collections.singletonList(new Room("1",2)));
        int expected = 2;
        //when
        int actual = bookingService.getAvailablePlaceCount();
        //then
        assertEquals(expected,actual);
    }

    @Test
    void shouldCountaMultipleRoomsAvailable() {
        //given
        List<Room> roomList = Arrays.asList(new Room("1",2),new Room("2",5));
        when(this.roomServiceMock.getAvailableRooms()).thenReturn(roomList);
        int expected = 7;
        //when
        int actual = bookingService.getAvailablePlaceCount();
        //then
        assertEquals(expected,actual);
    }
}
