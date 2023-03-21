import { useParams } from "react-router-dom";
import Items from "../../../../components/Items";
import ItemsLoader from "./../../../../components/ItemsLoader";
import useListFood from "./../hook/useListFood";
const RenderingFood = () => {
	const { idCategory } = useParams();
	const { list, isLoading } = useListFood(idCategory);
	return (
		<>
			{isLoading ? (
				<>
					{list.map((item) => (
						<Items
							key={item.id}
							category={item.category.name}
							description={item.description}
							id={item.id}
							image={item.image.url}
							people={item.people}
							price={item.price}
							title={item.name}
							blockSize='240px'
							inlineSize='240px'
							insetInline="18%"
							top='-24%'
						/>
					))}
				</>
			) : (
				<>
					<ItemsLoader
						borderRadius='50%'
						blockSize='240px'
						inlineSize='240px'
						top='-60%'
						insetInline='14%'
					/>
					<ItemsLoader
						borderRadius='50%'
						blockSize='240px'
						inlineSize='240px'
						top='-60%'
						insetInline='14%'
					/>
					<ItemsLoader
						borderRadius='50%'
						blockSize='240px'
						inlineSize='240px'
						top='-60%'
						insetInline='14%'
					/>
					<ItemsLoader
						borderRadius='50%'
						blockSize='240px'
						inlineSize='240px'
						top='-60%'
						insetInline='14%'
					/>
					<ItemsLoader
						borderRadius='50%'
						blockSize='240px'
						inlineSize='240px'
						top='-60%'
						insetInline='14%'
					/>
					<ItemsLoader
						borderRadius='50%'
						blockSize='240px'
						inlineSize='240px'
						top='-60%'
						insetInline='14%'
					/>
					<ItemsLoader
						borderRadius='50%'
						blockSize='240px'
						inlineSize='240px'
						top='-60%'
						insetInline='14%'
					/>
				</>
			)}
		</>
	);
};

export default RenderingFood;
