import { HeaderSubTitle } from "../../../../components";
import { Div } from "../../../../styled-components";
import Card from "../components/Card";
import useCategories from "../hook/useCategories";
import CategoryLoader from "./CategoryLoader";

const Section = () => {
	const { categories, isSuccess } = useCategories();

	return (
		<section>
			<HeaderSubTitle
				title=' Conoce nuestra Carta'
				textAlign='center'
				level={1}
				fontWeight={300}
			/>

			<Div gap={"109px"} inlineSize='100%' blockSize='735px'>
				{isSuccess ? (
					categories.map((item) => (
						<Card
							key={item.id}
							img={item.image.url}
							link={item.name}
							name={item.name}
						/>
					))
				) : (
					<>
						<CategoryLoader />
						<CategoryLoader />
						<CategoryLoader />
						<CategoryLoader />
						<CategoryLoader />
						<CategoryLoader />
					</>
				)}
			</Div>
		</section>
	);
};

export default Section;
