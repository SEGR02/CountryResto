import DivisionLine from "../../../components/DivisionLine";
import useDisplacement from "../hook/useDisplacement";
import {
	ButtonSearchStyled,
	FormStyled,
	SectionHeroStyled,
} from "../styled-components/layout.styled";
import Carrusel from "./Carrusel";
import ContainerButton from "./ContainerButton";
import Input from "./Input";
import imgHome from "/assets/img/fondoHome.jpg";
import search from "/assets/icons/search.svg";

const SectionHero = ({ handleActive, onChange, textSearch, active }) => {
	const { move, handleMoveLeft, handleMoveRight } = useDisplacement();

	const handleOnSubmit = (event) => {
		event.preventDefault();
	};

	return (
		<SectionHeroStyled img={imgHome} size='cover'>
			<FormStyled onSubmit={handleOnSubmit} active={active}>
				<Input
					name={"q"}
					type={"search"}
					onFocus={handleActive}
					onChange={onChange}
					value={textSearch}
				/>
				<ButtonSearchStyled img={search} size='cover' />
			</FormStyled>

			<h1>DESTACADOS DE ESTA SEMANA</h1>

			<DivisionLine title={"Tablas para compartir"} />
			<Carrusel
				move={move}
				handleMoveLeft={handleMoveLeft}
				handleMoveRight={handleMoveRight}
			/>
			<ContainerButton
				handleMoveRight={handleMoveRight}
				handleMoveLeft={handleMoveLeft}
			/>
		</SectionHeroStyled>
	);
};

export default SectionHero;
