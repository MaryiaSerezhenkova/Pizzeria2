package pizza.api.validators;

import java.util.ArrayList;
import java.util.List;

import pizza.api.dto.TicketDTO;
import pizza.api.exceptions.ValidationException;

public class TicketValidator implements IValidator<TicketDTO> {
    @Override
    public void validate(TicketDTO ticketDTO) {
        List<String> errors = new ArrayList<>();

        if (ticketDTO == null) {
            errors.add("TicketDTO is null");
        } else {
            if (ticketDTO.getOrderId() == null) {
                errors.add("Order is null");
            }

            if (ticketDTO.getNumber()<0) {
                errors.add("Ticket number is not valid");
            } 
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(String.join(", ", errors));
        }
    }
}