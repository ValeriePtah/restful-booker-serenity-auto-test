package booker.booking;

import booker.BaseBookerTest;
import booker.util.BookingHelper;
import booker.util.BookingSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class BookingServiceTest extends BaseBookerTest {

    @Steps
    BookingSteps steps;

    @Test
    public void canCreateBooking() {
        steps.givenCorrectBookingInput();
        steps.responseIsCreated();
        steps.createdBookingInfoIsReturned();
    }

    @Test
    public void canGetBooking() {
        steps.givenCorrectBookingIdIsProvided(BookingHelper.randomBookingID());
        steps.responseIsSuccessful();
        steps.fullBookingInfoIsReturned();
    }

    @Test
    public void canGetAllBookings() {
        steps.whenRequestingListOfBookings();
        steps.responseIsSuccessful();
        steps.listOfBookingsIsReturned();
    }

    @Test
    public void canUpdateBooking() {
        Integer bookingID = BookingHelper.randomBookingID();
        steps.givenCorrectBookingIdIsProvided(bookingID);
        steps.updateIsApplied(bookingID);
        steps.responseIsSuccessful();
        steps.bookingEntryIsUpdated();
    }

    @Test
    public void canPatchBooking() {
        Integer bookingID = BookingHelper.randomBookingID();
        steps.givenCorrectBookingIdIsProvided(bookingID);
        steps.patchWithToken(bookingID);
        steps.responseIsSuccessful();
        steps.bookingEntryIsPatched();
    }

    @Test
    public void canDeleteBooking() {
        steps.givenCorrectBookingInput();
        steps.createdBookingInfoIsReturned();
        steps.bookingIsDeleted();
        steps.responseIsSuccessful();
        steps.givenCorrectBookingIdIsProvided(steps.fetchCreatedBookingID());
        steps.responseIsNotFound();
    }
}
