import { useMemo, useState } from "react";
import { useGetDishesQuery } from "../../../redux/query/FoodInfo.query";
const useSearch = () => {
	const [active, setActive] = useState(false);
	const [textSearch, setTextSearch] = useState("");

	const handleActive = () => setActive(true);
	const handleDesActive = () => setActive(false);
	const handleOnchange = (event) => setTextSearch(event.target.value);

	const { data: dis, isSuccess } = useGetDishesQuery();

	const template = useMemo(() => {
		if (isSuccess && textSearch !== "") {
			const { dishes } = dis._embedded;
			return dishes.filter((items) =>
			items.name.toLowerCase().includes(textSearch.toLowerCase()),
			);
		}
		return [];
	}, [textSearch]);
	return {
		active,
		handleActive,
		handleDesActive,
		handleOnchange,
		textSearch,
		template,
		isSuccess,
	};
};

export default useSearch;
