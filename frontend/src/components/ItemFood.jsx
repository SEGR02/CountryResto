import { Link, Outlet, useParams } from "react-router-dom";
import { ItemFoodStyled } from "../pages/view/categoria/styled-components/layout.styled";
import Star from "./Star";
import cart from "/assets/icons/cart.svg";
const ItemFood = ({
	description,
	price,
	nameFood,
	portion,
	id,
	img,
	category,
}) => {
	const { namePath } = useParams();
	return (
		<>
			{!namePath ? (
				<ItemFoodStyled>
					<header>
						<img src={img} alt={nameFood} loading='lazy' />
					</header>
					<Star />
					<h2>{nameFood}</h2>
					<b>{description}</b>
					<p>{portion} porciones</p>
					<strong>${price}</strong>

					<Link to={`/categoria/${category}/${id}`}>
						<strong>Agregar al Carrito</strong>
						<img src={cart} alt="image-cart" />
					</Link>
				</ItemFoodStyled>
			) : (
				<Outlet />
			)}
		</>
	);
};

export default ItemFood;
