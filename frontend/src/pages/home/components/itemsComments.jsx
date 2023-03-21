import { ItemsCommentsStyled } from "../styled-components/layout.styled";
const ItemsComments = ({ img, name, comment, date }) => {
	return (
		<ItemsCommentsStyled>
			<header>
				<img src={img} alt={name} />
			</header>
			<div>
				<h3>{name}</h3>
				<hr />
				<p>{comment}</p>
				<span>{date}</span>
			</div>
		</ItemsCommentsStyled>
	);
};

export default ItemsComments;
