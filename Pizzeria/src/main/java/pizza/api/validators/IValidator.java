package pizza.api.validators;

public interface IValidator<TYPE> {
	void validate(TYPE t);

}
