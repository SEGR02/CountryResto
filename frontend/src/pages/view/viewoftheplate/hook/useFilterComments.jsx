import { useEffect, useState } from "react";
import { useGetCommentsQuery } from "../../../../redux/query/FoodInfo.query";
const useFilterComments = (idFood) => {
	const [items, setItems] = useState([]);

	const { data: info, isSuccess } = useGetCommentsQuery(idFood);

	useEffect(() => {
		if (isSuccess && info._embedded !== undefined) {
			const { comments } = info._embedded;
			setItems([...comments]);
		}
	}, [isSuccess]);
	console.log(items);
	return {
		items,
		isSuccess,
	};
};

export default useFilterComments;
