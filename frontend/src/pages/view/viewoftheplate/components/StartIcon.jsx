import { StarIconStyled } from "../styled-components/layout.styled";
const StarIcon = (props) => {
	const { fill = "none" } = props;

	return (
		<StarIconStyled
			width="25"
			height="26"
			viewBox="0 0 25 26"
			fill={fill}
			xmlns="http://www.w3.org/2000/svg"
		>
			<path
				d="M12.5 0.888198L15.0665 9.63789L15.1192 9.81752H15.3064H23.6788L16.8841 15.2858L16.754 15.3906L16.801 15.5509L19.3831 24.3536L12.6567 18.9403L12.5 18.8141L12.3433 18.9403L5.61695 24.3536L8.199 15.5509L8.24604 15.3906L8.11585 15.2858L1.32118 9.81752H9.69357H9.88078L9.93347 9.63789L12.5 0.888198Z"
				stroke="#FFC451"
				strokeWidth="0.5"
			/>
		</StarIconStyled>
	);
};

export default StarIcon;
