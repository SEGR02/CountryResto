import { InputStyled } from "../styled-components/layout.styled";
const Input = ({ type, name, onFocus, value, onChange }) => {
	return (
		<InputStyled
			type={type}
			id='q'
			name={name}
			placeholder="Buscar"
			onFocus={onFocus}
			onChange={onChange}
			value={value}
		/>
	);
};

export default Input;
