package pizza.api.validators;

import pizza.api.dto.TicketDTO;

public class TicketValidatorSingleton {
	private static volatile TicketValidatorSingleton instance;

	private final IValidator<TicketDTO> ticketValidator;

	private TicketValidatorSingleton() {
		ticketValidator = new TicketValidator();
	}

	public static IValidator<TicketDTO> getInstance() {
		if (instance == null) {
			synchronized (TicketValidatorSingleton.class) {
				if (instance == null) {
					instance = new TicketValidatorSingleton();
				}
			}
		}

		return instance.ticketValidator;
	}
}

