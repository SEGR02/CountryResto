import {
	FooterContainerStyled,
	FooterContainerTwo,
	FooterStyled,
} from "../styled-components";
import face from "/assets/icons/facebook.svg";
import location from "/assets/icons/location.svg";
import logo from "/assets/icons/logoHeader.svg";
import phone from "/assets/icons/phone.svg";
import twitter from "/assets/icons/twitter.svg";
import WhatsApp from "/assets/icons/whatsapp.svg";
const Footer = () => {
	return (
		<FooterStyled>
			<img src={logo} alt="SVG logo image" />
			<FooterContainerStyled>
				<div>
					<span>Encontranos</span>
					<ul>
						<li>
							<img src={location} alt="SVG location image" />
							<p>Urquiza 2345 - Capital</p>
						</li>
					</ul>
				</div>
				<div>
					<span>Reservas</span>
					<ul>
						<li>
							<img src={phone} alt="SVG phone image" />
							<p>11 - 23232323</p>
						</li>
					</ul>
				</div>
				<div>
					<span>WhatsApp</span>
					<ul>
						<li>
							<img src={WhatsApp} alt="SVG phone image" />
							<p>11- 45454545</p>
						</li>
					</ul>
				</div>
			</FooterContainerStyled>

			<FooterContainerTwo>
				<span>Seguinos</span>
				<ul>
					<li>
						<img src={face} alt="SVG facebook image" />
					</li>
					<li>
						<img src={twitter} alt="SVG twitter image" />
					</li>
				</ul>
			</FooterContainerTwo>
		</FooterStyled>
	);
};

export default Footer;
