import { useEffect, useState } from "react";
import { useGetInfoFoodQuery } from "../redux/query/FoodInfo.query";

const useModalFunctional = (id, nameFood) => {
	const [itemFood, setItemFood] = useState();

	const { data: food, isSuccess } = useGetInfoFoodQuery();

	useEffect(() => {
		if (isSuccess) {
			let template = 0;
			food.forEach((item) => {
				if (item.name === nameFood) {
					template = item.list;
				}
			});

			const itemFood = template.filter((item) => item.id === Number(id));
			setItemFood(...itemFood);
		}
	}, []);

	return {
		itemFood,
	};
};

export default useModalFunctional;
