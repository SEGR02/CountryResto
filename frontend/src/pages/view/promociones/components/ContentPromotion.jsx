import {
	CardList,
	DivisionLine,
	Footer,
	Header,
	HeaderSubTitle,
	ItemsLoader,
} from "../../../../components";
import Items from "../../../../components/Items";
import NavSide from "../../../../components/NavSide";
import { useFilterDishes } from "../../../../hooks";
import { Div } from "../../../../styled-components";
import { SectionStyled } from "../styled-components/layout.styled";
import { filterPromotions } from "../utilities/filterPromotions";
import promotions from "/assets/img/promotionImg.jpg";
const ContentPromotion = () => {
	const { list, isSuccess } = useFilterDishes(filterPromotions);

	return (
		<>
			<NavSide />
			<Header />
			<CardList />
			<SectionStyled img={promotions} size='cover'>
				<HeaderSubTitle
					title='PROMO ESPECIAL DE FIN DE SEMANA'
					textAlign='center'
					level={2}
				/>

				<DivisionLine title={"Platos con descuentos"} />

				<Div gap={"180px"} padding='8em 0'>
					{isSuccess ? (
						list.length === 0 ? (
							<div>Sin Promociones</div>
						) : (
							list.map((item) => (
								<Items
									key={item.id}
									category={item.category.name}
									description={item.description}
									id={item.id}
									image={item.image.url}
									people={item.people}
									price={item.price}
									title={item.name}
									blockSize='192px'
									inlineSize='300px'
									insetInline="10%"
									top='-15%'
									hasPromotion={item.hasPromotion}
								/>
							))
						)
					) : (
						<>
							<ItemsLoader />
							<ItemsLoader />
							<ItemsLoader />
							<ItemsLoader />
							<ItemsLoader />
						</>
					)}
				</Div>
			</SectionStyled>
			<Footer />
		</>
	);
};

export default ContentPromotion;
