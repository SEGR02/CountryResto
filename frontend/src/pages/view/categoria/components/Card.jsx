import { Link } from "react-router-dom";
import { CardStyled } from "../styled-components/layout.styled";
const Card = ({ link, name, id, img }) => {
	return (
		<CardStyled key={id} img={img} cover='cover'>
			<Link to={link}>
				<h2>{name.toUpperCase()}</h2>
			</Link>
		</CardStyled>
	);
};

export default Card;
