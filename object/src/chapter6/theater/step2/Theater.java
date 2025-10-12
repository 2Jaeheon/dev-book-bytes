package chapter6.theater.step2;

public class Theater {

    private TicketSeller ticketSeller;

    public Theater(TicketSeller ticketSeller) {
        this.ticketSeller = ticketSeller;
    }

    public void enter(Audience audience) {
        // 디미터 법칙을 어겼음
        // Audience가 Bag을 포함한다는 것은 내부 구현에 속함.
        // Theater는 Audience와 TicketSeller에게 묻지 않고 시키도록 수정해야함.
        /*if (audience.getBag().hasInvitation()) {
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
            audience.getBag().setTicket(ticket);
        } else {
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
            audience.getBag().minusAmount(ticket.getFee());
            ticketSeller.getTicketOffice().plusAmount(ticket.getFee());
            audience.getBag().setTicket(ticket);
        }*/

        ticketSeller.sellTo(audience);
    }
}

