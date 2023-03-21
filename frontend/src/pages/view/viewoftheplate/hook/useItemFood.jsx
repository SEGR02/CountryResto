import { useEffect, useState } from "react";
import { useGetDishesQuery } from "../../../../redux/query/FoodInfo.query";

const useItemFood = (id, nameFood) => {
	const [itemFood, setItemFood] = useState([]);

	const { data: food, isSuccess } = useGetDishesQuery();
	useEffect(() => {
		if (isSuccess) {
			const { dishes } = food._embedded;

			const template = dishes.filter(
				(item) => item.category.name === nameFood && item.id === Number(id),
			);

			setItemFood(...template);
		}
	}, [isSuccess]);

	return {
		itemFood,
		isSuccess,
	};
};

export default useItemFood;
