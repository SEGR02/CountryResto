import { ContainerFormClaimsStyled } from "../styled-components";
import HeaderSubTitle from "./../../../components/HeaderSubTitle";
const Claims = () => {
	return (
		<section>
			<HeaderSubTitle title='Reclamos' textAlign='left' level={2} />
			<p>Deja tu reclamo aquí </p>
			<ContainerFormClaimsStyled>
				<textarea />
			</ContainerFormClaimsStyled>
			<article>
				<HeaderSubTitle title='Reserva' textAlign='left' level={2} />
				<p>Descripción </p>
				<p>Contactos </p>
			</article>
		</section>
	);
};

export default Claims;
