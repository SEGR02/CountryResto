import { useDispatch, useSelector } from "react-redux";
import { openCart } from "../redux/slice/cart.slice";

import { Link } from "react-router-dom";
import { NavBar } from ".";
import { HeaderStyled } from "../styled-components";
import ButtonAction from "./ButtonAction";
import NavBottom from "./NavBottom";
import Tabbar from "./Tabbar";
import cart from "/assets/icons/carWhite.svg";
import logo from "/assets/icons/logoHeader.svg";

const Header = () => {
	const dispatch = useDispatch();
	const car = useSelector((state) => state.cart.items);
	const handleOpen = () => dispatch(openCart());

	return (
		<HeaderStyled>
			<Tabbar />
			<Link to='/'>
				<img src={logo} alt='country' />
			</Link>
			<NavBar />
			<ButtonAction
				img={cart}
				size="cover"
				backgroundColor={"transparent"}
				onClick={handleOpen}
				padding={"1em"}
				dataLength={car.length}
				opacity={car.length === 0}
			/>
			<NavBottom />
		</HeaderStyled>
	);
};

export default Header;
