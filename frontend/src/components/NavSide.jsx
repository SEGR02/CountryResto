import { useDispatch, useSelector } from "react-redux";
import { NavLink } from "react-router-dom";
import { openMenu } from "../redux/slice/cart.slice";
import { ButtonCarListExitStyled, NavSideStyled } from "../styled-components";
import crossIcon from "/assets/icons/cross-icon.svg";
import logo from "/assets/icons/logo.svg";

const NavSide = () => {
	const dispatch = useDispatch();
	const open = useSelector((state) => state.cart.openMenu);
	const accessToken = useSelector((state) => state.auth.accessToken);

	const handleMenu = () => dispatch(openMenu(false));

	return (
		<NavSideStyled translateX={open ? "translateX(0%)" : "translateX(-100%)"}>
			<header>
				<img src={logo} alt='country' />
				<ButtonCarListExitStyled onClick={handleMenu} img={crossIcon} />
			</header>
			<menu>
				<li onClick={handleMenu}>
					<NavLink to={"/login"}>Iniciar sesión</NavLink>
				</li>
				<li onClick={handleMenu}>
					<NavLink to={""}>Mi carrito</NavLink>
				</li>
				<li onClick={handleMenu}>
					<NavLink to={accessToken ? "/myaccount" : "/login"}>
						Mis datos
					</NavLink>
				</li>
				<li onClick={handleMenu}>
					<NavLink to={""}>Mis tarjetas</NavLink>
				</li>
				<li onClick={handleMenu}>
					<NavLink to={""}>Mis pedidos</NavLink>
				</li>
				<li onClick={handleMenu}>
					<NavLink to={"/contacto"}>Nosotros</NavLink>
				</li>
				<li onClick={handleMenu}>
					<NavLink to={""}>Reservas</NavLink>
				</li>
				<li onClick={handleMenu}>
					<NavLink to={"/logout"}>Cerrar sesión</NavLink>
				</li>
			</menu>
		</NavSideStyled>
	);
};

export default NavSide;
