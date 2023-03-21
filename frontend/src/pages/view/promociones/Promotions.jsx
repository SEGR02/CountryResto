import { lazy, Suspense } from "react";
import { Loader } from "../../../components";
const Promotions = () => {
	document.title = "Promociones";
	const ContentPromotions = lazy(() => import("./components/ContentPromotion"));
	return (
		<Suspense fallback={<Loader />}>
			<ContentPromotions />
		</Suspense>
	);
};

export default Promotions;
