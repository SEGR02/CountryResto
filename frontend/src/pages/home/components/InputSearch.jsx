import {
	ButtonSearchStyled,
	SearchStyle,
} from "./../styled-components/layout.styled";
import search from "/icons/search.svg";
const InputSearch = ({ type, onFocus, onBlur, value }) => {
	return (
		<SearchStyle>
			<input
				type={type}
				placeholder='Buscar'
				onFocus={onFocus}
				onBlur={onBlur}
				value={value}
			/>
			<ButtonSearchStyled img={search} size='cover' />
		</SearchStyle>
	);
};

export default InputSearch;
