import React from "react";
import { CardList, Header } from "../../../../components";
import NavSide from "../../../../components/NavSide";
import { MyAccountStyle } from "../../styled-components/MyAccountComponent";
import cardImg from "./image/cardsvg.svg";
import cartImg from "./image/cartsvg.svg";
import logOutImg from "./image/logOutsvg.svg";
import LogOut from "./LogOut/LogOut";
import MisTarjetas from "./MyCards/MisTarjetas";
import MisDatos from "./MyData/MisDatos";
import MyOrders from "./MyOrders/MyOrders";
export default function MyAccount() {
	const [misDatos, setMisDatos] = React.useState(true);
	const [misTarjetas, setMisTarjetas] = React.useState(false);
	const [misPedidos, setMisPedidos] = React.useState(false);
	const [cerrarSesion, setCerrarSesion] = React.useState(false);
	document.title = "Mi Cuenta";
	function setAuto(set) {
		setMisDatos(false);
		setMisTarjetas(false);
		setMisPedidos(false);
		setCerrarSesion(false);
		set(true);
	}

	const activacion = (e) => {
		let boton = e.target.name;
		switch (boton) {
			case "Datos":
				setAuto(setMisDatos);

				break;
			case "Tarjetas":
				setAuto(setMisTarjetas);

				break;
			case "Pedidos":
				setAuto(setMisPedidos);

				break;
			case "Sesion":
				setAuto(setCerrarSesion);

				break;
			default:
				null;
		}
	};

	return (
		<>
			<NavSide />
			<Header />
			<CardList />
			<MyAccountStyle>
				<div className="MyAccount">
					<p>Mi Cuenta</p>
					<div className="line" />
					<div className="container">
						<div className="apartados">
							<div className="myData">
								<div id="mascara" />
								<button name="Datos" onClick={(e) => activacion(e)}>
									Mis datos
								</button>
							</div>
							<div className="myCards">
								<object data={cardImg} />
								<button name="Tarjetas" onClick={(e) => activacion(e)}>
									Mis tarjetas
								</button>
							</div>
							<div className="myOrders">
								<img src={cartImg} alt='logo' />
								<button name="Pedidos" onClick={(e) => activacion(e)}>
									Mis pedidos
								</button>
							</div>
							<div className="logOut">
								<img src={logOutImg} alt='logo' />
								<button name="Sesion" onClick={(e) => activacion(e)}>
									Cerrar Sesión
								</button>
							</div>
						</div>
						<div className="line2" />
						<div className="data">
							{misDatos && <MisDatos />}
							{misTarjetas && <MisTarjetas />}
							{misPedidos && <MyOrders />}
							{cerrarSesion && <LogOut />}
						</div>
					</div>
				</div>
			</MyAccountStyle>
		</>
	);
}
