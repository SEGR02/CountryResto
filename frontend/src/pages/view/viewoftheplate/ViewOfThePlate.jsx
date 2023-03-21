import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { HeroImage } from "../../../components";
import Item from "../../../model/item.model";
import { resetValue } from "../../../redux/slice/AppetizerData.slice";
import { addItems } from "../../../redux/slice/cart.slice";
import Container from "./components/Container";
import Description from "./components/Description";
import FooterArticle from "./components/FooterArticle";
import Form from "./components/Form";
import HeaderArticle from "./components/HeaderArticle";
import SectionClients from "./components/SectionClients";
import ViewOfThePlateLoader from "./components/ViewOfThePlateLoader";
import useItemFood from "./hook/useItemFood";
import {
	ArticleStyled,
	ViewOfThePlateStyled,
} from "./styled-components/layout.styled";
const ViewOfThePlate = () => {
	const amount = useSelector((state) => state.AppetizerData);

	const dispatch = useDispatch();

	const { idCategory, idFood } = useParams();

	const { itemFood, isSuccess } = useItemFood(idFood, idCategory);

	const handleAddItems = () => {
		if (amount.value !== 0) {
			const itemCart = new Item(
				itemFood.id,
				amount.value,
				itemFood.name,
				itemFood.price,
				amount.value * itemFood.price,
				"",
				"",
			);
			dispatch(addItems(itemCart));
		}
		dispatch(resetValue());
	};

	return (
		<ViewOfThePlateStyled>
			<Container>
				{isSuccess && itemFood !== undefined && itemFood.image !== undefined ? (
					<>
						<HeroImage
							img={itemFood.image.url}
							alt={itemFood.name}
							blockSize='400px'
							inlineSize='400px'
						/>
						<ArticleStyled>
							<HeaderArticle nameFood={itemFood.name} qualification={"4.9"} />

							<Description
								description={itemFood.description}
								price={itemFood.price}
								delay={"15 a 20 Min de demora"}
							/>
							<FooterArticle
								handleAddItems={handleAddItems}
								value={amount.value}
							/>
						</ArticleStyled>
					</>
				) : (
					<ViewOfThePlateLoader />
				)}
			</Container>
			<hr />
			<Container
				flexDirection='column'
				padding='0em 6em'
				alignItems='flex-start'
			>
				<Container flexDirection='column' alignItems='flex-start'>
					<Form />
					<SectionClients />
				</Container>
			</Container>
		</ViewOfThePlateStyled>
	);
};

export default ViewOfThePlate;
