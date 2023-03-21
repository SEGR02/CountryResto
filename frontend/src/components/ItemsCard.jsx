import { useDispatch } from "react-redux";
import { deleteItem } from "../redux/slice/cart.slice";
import {
	ButtonDeleteItemCartStyled,
	ItemCartStyled,
} from "../styled-components";
import dele from "/assets/icons/delete.svg";

const ItemsCard = ({ id, amount, name, cost }) => {
	const dispatch = useDispatch();

	return (
		<ItemCartStyled>
			<p>
				<span>{amount}X</span>
				<span>{name}</span>
			</p>
			<div>
				<p>${cost}</p>
				<ButtonDeleteItemCartStyled
					onClick={() => dispatch(deleteItem(id))}
					img={dele}
					size='50%'
				/>
			</div>
		</ItemCartStyled>
	);
};

export default ItemsCard;
