import { CheckedStyled } from "../CheckoutStyled";
const Checked = ({ labelText, id, name, checked, onChange, value }) => {
	return (
		<CheckedStyled>
			<input
				type="radio"
				id={id}
				name={name}
				checked={checked}
				value={value}
				onChange={onChange}
			/>
			<label htmlFor={id}>{labelText}</label>
		</CheckedStyled>
	);
};

export default Checked;
