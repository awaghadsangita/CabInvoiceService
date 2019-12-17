package cabinvoicegenerator;

import org.junit.Assert;
import org.junit.Test;

public class InvoiceServiceTest {

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        try {
            InvoiceService invoiceService = new InvoiceService(InvoiceService.JourneyType.NORMAL);
            double distance = 2.0;
            int time = 5;
            double fare = 0;
            fare = invoiceService.calculateFare(distance, time);
            Assert.assertEquals(25, fare, 0.0);
        } catch (InvoiceServiceException e) {
        }
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinimumFare() {
        try {
            InvoiceService invoiceService = new InvoiceService(InvoiceService.JourneyType.NORMAL);
            double distance = 0.1;
            int time = 1;
            double fare = 0;
            fare = invoiceService.calculateFare(distance, time);
            Assert.assertEquals(5, fare, 0.0);
        } catch (InvoiceServiceException e) {
        }
    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        try {
            InvoiceService invoiceService = new InvoiceService(InvoiceService.JourneyType.NORMAL);
            Ride[] rides = {new Ride(2.0, 5),
                    new Ride(0.1, 1)
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
            InvoiceService invoiceService = new InvoiceService(InvoiceService.JourneyType.NORMAL);
            String userId = "a@b.com";
            Ride[] rides = {new Ride(2.0, 5),
                    new Ride(0.1, 1)
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
            InvoiceService invoiceService = new InvoiceService(InvoiceService.JourneyType.PREMIUM);
            String userId = "sangita@gmail.com";
            double distance = 15;
            int time = 2;
            double fare = 0;
            fare = invoiceService.calculateFare(distance, time);
            Assert.assertEquals(229.0, fare, 0.0);
        } catch (InvoiceServiceException e) {
        }
    }
    @Test
    public void givenMultiplePremiumRides_ShouldReturnInvoiceSummary() {
        try {
            InvoiceService invoiceService = new InvoiceService(InvoiceService.JourneyType.PREMIUM);
            Ride[] rides = {new Ride(2.0, 5),
                    new Ride(0.1, 1)
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
            InvoiceService invoiceService = new InvoiceService(InvoiceService.JourneyType.PREMIUM);
            String userId = "a@b.com";
            Ride[] rides = {new Ride(2.0, 5),
                    new Ride(0.1, 1)
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
            InvoiceService invoiceService = new InvoiceService(InvoiceService.JourneyType.PREMIUM);
            String userId = null;
            Ride[] rides = {new Ride(2.0, 5),
                    new Ride(0.1, 1)
            };
            invoiceService.addRides(userId, rides);
            InvoiceSummary summary = null;
            summary = invoiceService.getInvoiceSummary(userId);
            InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 60.0);

        } catch (InvoiceServiceException e) {
            Assert.assertEquals(InvoiceServiceException.ExceptionType.INVALID_USER,e.type);
        }
    }

}
