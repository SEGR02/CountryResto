import { Link } from "react-router-dom";
import {
	ItemContainerStyled,
	ItemDescription,
	ItemFooterStyled,
	ItemImgStyled,
	ItemPeople,
	ItemPrice,
} from "./../styled-components/layout/layout.styled";
import Star from "./Star";
import car from "/assets/icons/cart.svg";
const Items = ({
	title,
	image,
	description,
	people,
	price,
	id,
	category,
	top,
	insetInline,
	blockSize,
	inlineSize,
	hasPromotion,
}) => {
	return (
		<ItemContainerStyled key={id}>
			<ItemImgStyled
				src={image}
				alt={title}
				inlineSize={inlineSize}
				blockSize={blockSize}
				borderRadius='29px'
				loading="lazy"
				top={top}
				insetInline={insetInline}
			/>
			{!hasPromotion && <Star />}
			<h2>{title}</h2>
			<ItemDescription>{description}</ItemDescription>
			<ItemPeople>Para {people} personas</ItemPeople>
			<ItemPrice>${price}</ItemPrice>
			<ItemFooterStyled>
				<Link to={`/categoria/${category}/${id}`}>
					<span>Agregar al Carrito</span>
					<img src={car} alt="image-cart" />
				</Link>
			</ItemFooterStyled>
		</ItemContainerStyled>
	);
};

export default Items;
