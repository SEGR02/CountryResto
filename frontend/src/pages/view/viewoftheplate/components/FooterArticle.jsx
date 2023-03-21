import { useDispatch } from "react-redux";
import {
	decrement,
	increment,
} from "../../../../redux/slice/AppetizerData.slice";
import {
	ContainerFooterStyled,
	FooterArticleStyled,
} from "../styled-components/layout.styled";

import cart from "/assets/icons/cart.svg";
const FooterArticle = ({ value, handleAddItems }) => {
	const dispatch = useDispatch();
	const handlerIncrement = () => dispatch(increment());
	const handlerDecrement = () => dispatch(decrement());

	return (
		<FooterArticleStyled>
			<span>Unidades</span>
			<ContainerFooterStyled>
				<button onClick={handlerIncrement}>+</button>
				<span>{value}</span>
				<button onClick={handlerDecrement}>-</button>
			</ContainerFooterStyled>
			<button onClick={handleAddItems}>
				<span>Agregar</span>
				<img src={cart} alt='cart' />
			</button>
		</FooterArticleStyled>
	);
};

export default FooterArticle;
