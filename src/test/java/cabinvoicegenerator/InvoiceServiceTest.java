package cabinvoicegenerator;

import org.junit.Assert;
import org.junit.Test;

public class InvoiceServiceTest {

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        try {
            InvoiceService invoiceService = new InvoiceService();
            double distance = 2.0;
            int time = 5;
            double fare = 0;
            fare = invoiceService.calculateFare(distance, time,Ride.RideType.NORMAL);
            Assert.assertEquals(25, fare, 0.0);
        } catch (InvoiceServiceException e) {
        }
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinimumFare() {
        try {
            InvoiceService invoiceService = new InvoiceService();
            double distance = 0.1;
            int time = 1;
            double fare = 0;
            fare = invoiceService.calculateFare(distance, time,Ride.RideType.NORMAL);
            Assert.assertEquals(5, fare, 0.0);
        } catch (InvoiceServiceException e) {
        }
    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        try {
            InvoiceService invoiceService = new InvoiceService();
            Ride[] rides = {new Ride(2.0, 5, Ride.RideType.NORMAL),
                    new Ride(0.1, 1, Ride.RideType.NORMAL)
            };
            InvoiceSummary summary = null;
            summary = invoiceService.calculateFare(rides);
            InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
            Assert.assertEquals(expectedInvoiceSummary, summary);
        } catch (InvoiceServiceException e) {
        }
    }

    @Test
    public void givenUserIdAndRides_ShouldReturnInvoiceSummary() {
        try {
            InvoiceService invoiceService = new InvoiceService();
            String userId = "a@b.com";
            Ride[] rides = {new Ride(2.0, 5, Ride.RideType.NORMAL),
                    new Ride(0.1, 1, Ride.RideType.NORMAL)
            };
            invoiceService.addRides(userId, rides);
            InvoiceSummary summary = null;
            summary = invoiceService.getInvoiceSummary(userId);
            InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
            Assert.assertEquals(expectedInvoiceSummary, summary);
        } catch (InvoiceServiceException e) {
        }
    }

    @Test
    public void givenUserId_WithPremiumRides_ShouldReturnInvoiceSummary() {
        try {
            InvoiceService invoiceService = new InvoiceService();
            String userId = "sangita@gmail.com";
            double distance = 15;
            int time = 2;
            double fare = 0;
            fare = invoiceService.calculateFare(distance, time,Ride.RideType.PREMIUM);
            Assert.assertEquals(229.0, fare, 0.0);
        } catch (InvoiceServiceException e) {
        }
    }
    @Test
    public void givenMultiplePremiumRides_ShouldReturnInvoiceSummary() {
        try {
            InvoiceService invoiceService = new InvoiceService();
            Ride[] rides = {new Ride(2.0, 5, Ride.RideType.PREMIUM),
                    new Ride(0.1, 1, Ride.RideType.PREMIUM)
            };
            InvoiceSummary summary = null;
            summary = invoiceService.calculateFare(rides);
            InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 60);
            Assert.assertEquals(expectedInvoiceSummary, summary);
        } catch (InvoiceServiceException e) {
        }
    }
    @Test
    public void givenUserId_WithPremiumWithMultipleRides_ShouldReturnInvoiceSummary() {
        try {
            InvoiceService invoiceService = new InvoiceService();
            String userId = "a@b.com";
            Ride[] rides = {new Ride(2.0, 5, Ride.RideType.PREMIUM),
                    new Ride(0.1, 1, Ride.RideType.PREMIUM)
            };
            invoiceService.addRides(userId, rides);
            InvoiceSummary summary = null;
            summary = invoiceService.getInvoiceSummary(userId);
            InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 60.0);
            Assert.assertEquals(expectedInvoiceSummary, summary);
        } catch (InvoiceServiceException e) {
        }
    }

    @Test
    public void givenNullUserId_WithPremiumWithMultipleRides_ShouldHandleException() {
        try {
            InvoiceService invoiceService = new InvoiceService();
            String userId = null;
            Ride[] rides = {new Ride(2.0, 5, Ride.RideType.PREMIUM),
                    new Ride(0.1, 1, Ride.RideType.PREMIUM)
            };
            invoiceService.addRides(userId, rides);
            InvoiceSummary summary = null;
            summary = invoiceService.getInvoiceSummary(userId);
            InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 60.0);

        } catch (InvoiceServiceException e) {
            Assert.assertEquals(InvoiceServiceException.ExceptionType.INVALID_USER,e.type);
        }
    }

    @Test
    public void givenUserId_WithMixedRideTypeWithMultipleRides_ShouldReturnInvoiceSummary() {
        try {
            InvoiceService invoiceService = new InvoiceService();
            String userId = "ab@gmail.com";
            Ride[] rides = {new Ride(2.0, 5,Ride.RideType.PREMIUM),
                    new Ride(0.1, 1, Ride.RideType.NORMAL)
            };
            invoiceService.addRides(userId, rides);
            InvoiceSummary summary = null;
            summary = invoiceService.getInvoiceSummary(userId);
            InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 45.0);
            Assert.assertEquals(expectedInvoiceSummary,summary);
        } catch (InvoiceServiceException e) {

        }
    }

    @Test
    public void givenUserId_WithMixedRideTypeWithNullTimeWithMultipleRides_ShouldHandleException() {
        try {
            InvoiceService invoiceService = new InvoiceService();
            String userId = "ab@gmail.com";
            Ride[] rides = {new Ride(2.0, null,Ride.RideType.PREMIUM),
                    new Ride(0.1, null, Ride.RideType.NORMAL)
            };
            invoiceService.addRides(userId, rides);
        } catch (InvoiceServiceException e) {
            Assert.assertEquals(InvoiceServiceException.ExceptionType.INVALID_INPUT,e.type);
        }
    }

    @Test
    public void givenProperUserId_WhenRidesAreMultiplrAreGiven_ShouldGetsAddedToRideRepository() {
        InvoiceService invoiceService = new InvoiceService();
        String userId = "shivanjali@gamil.com";
        try {
            Ride[] ride1 = {new Ride(2.0, 5,Ride.RideType.PREMIUM),
                    new Ride(0.1, 1, Ride.RideType.NORMAL)
            };
            Ride[] ride2 = {new Ride(2.0, 5,Ride.RideType.PREMIUM),
                    new Ride(0.1, 1, Ride.RideType.NORMAL)
            };
            Ride[] ride3 = {new Ride(2.0, 5,Ride.RideType.PREMIUM),
                    new Ride(0.1, 1, Ride.RideType.NORMAL)
            };
            invoiceService.addRides(userId, ride1);
            invoiceService.addRides(userId, ride2);
            invoiceService.addRides(userId, ride3);
            InvoiceSummary summary = null;
            summary = invoiceService.getInvoiceSummary(userId);
            InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(6, 135);
            Assert.assertEquals(expectedInvoiceSummary,summary);
        } catch (InvoiceServiceException e) {}

    }
}
