import { DescriptionStyled } from "../styled-components/layout.styled";
const Description = ({ description, delay, price }) => {
	return (
		<DescriptionStyled>
			<strong>Descripción del plato</strong>
			<p>{description}</p>
			<p>{delay}</p>
			<p>${price}</p>
		</DescriptionStyled>
	);
};

export default Description;
