import { SubtitleStyled } from "../CheckoutStyled";
const SubTitle = ({ title, bottom }) => {
	return (
		<SubtitleStyled bottom={bottom}>
			<h2>{title}</h2>
		</SubtitleStyled>
	);
};

export default SubTitle;
