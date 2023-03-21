import { StarStyled } from "../pages/view/categoria/styled-components/layout.styled";
import star from "/assets/icons/startYellow.svg";
const Star = () => {
	return (
		<StarStyled>
			<img src={star} alt='star' />
			<span>4.9</span>
		</StarStyled>
	);
};

export default Star;
