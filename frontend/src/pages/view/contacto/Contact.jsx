import { CardList, Header } from "../../../components";
import NavSide from "../../../components/NavSide";
import {
	ContactForm,
	ContactSection,
	ContactStyled,
} from "./styled-components/layout.styled";
import location from "/assets/icons/location.svg";
import phone from "/assets/icons/phone.svg";
import wha from "/assets/icons/whatsapp.svg";

const Contact = () => {
	document.title = "Contacto";
	return (
		<>
			<NavSide />
			<Header />
			<CardList />
			<ContactStyled>
				<ContactSection>
					<h2>Nosotros</h2>
					<p>
						Somos una empresa dedicada a la gastronomía desde 1995, fundada en
						la ciudad de Buenos Aires. Nuestro objetivo siempre fue brindar al
						publico una mejor experiencia disgustando nuestros platos elaborados
						por el Chef Héctor Martínez, quien nos acompaña desde siempre.
						Estamos orgullosamente felices de que nos sigan eligiendo en cada
						encuentro familiar.
					</p>
				</ContactSection>

				<ContactSection>
					<h2>Contacto</h2>
					<ul>
						<li>
							<img src={phone} alt='phone' />
							<span>Teléfono: &nbsp;&nbsp; 11 - 23232323</span>
						</li>
						<li>
							<img src={wha} alt='WhatsApp' />
							<span>Whatsapp: &nbsp;&nbsp; 11 - 45454545</span>
						</li>
						<li>
							<img src={location} alt='location' />
							<span>Sucursal: &nbsp;&nbsp; Urquiza 2345 - Capital</span>
						</li>
					</ul>
				</ContactSection>

				<ContactSection>
					<h2>Reclamos</h2>
					<ContactForm onSubmit={(e) => handleSubmit(e)}>
						<label>Puedes dejar tu reclamo</label>
						<input type="text" />
						<button type="submit">Enviar</button>
					</ContactForm>
				</ContactSection>

				<ContactSection>
					<h2>Reserva</h2>
					<p>
						Para realizar una reserva se recomienda tener en cuenta una
						anticipación de 24hs.
						<br /> Podrás consultar disponibilidad a través de nuestros
						contactos telefónicos o acercándote a la recepción de nuestra
						sucursal.
					</p>
					<ul>
						<li>
							<img src={phone} alt='location' />
							Teléfono: 11 - 23232323
						</li>
					</ul>
				</ContactSection>
			</ContactStyled>
		</>
	);
};

export default Contact;
