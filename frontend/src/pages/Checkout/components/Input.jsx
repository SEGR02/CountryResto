import { InputStyled } from "../CheckoutStyled";
const Input = ({
	textLabel,
	type,
	name,
	placeholder,
	inlineSize,
	bottom,
	register,
	error,
}) => {
	return (
		<InputStyled inlineSize={inlineSize} bottom={bottom} outline={error}>
			<label htmlFor={name}>{textLabel}</label>
			<input type={type} name={name} placeholder={placeholder} {...register} />

			{error && <span>{error?.message}</span>}
		</InputStyled>
	);
};
export default Input;
