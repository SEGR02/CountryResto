import { createHashRouter } from "react-router-dom";
import Loader from "../components/Loader";
import AdminCreate from "../pages/Admin/AdminCreate.jsx";
import Auth from "../pages/Auth/Auth";
import Checkout from "../pages/Checkout/Checkout";
import Thanks from "../pages/Checkout/Thanks";
import MyAccount from "../pages/home/components/MyAccount/MyAccount";
import ItemsCategory from "../pages/view/categoria/components/ItemsCategory";
import Contact from "../pages/view/contacto/Contact";
import Promotions from "../pages/view/promociones/Promotions";
import ViewOfThePlate from "../pages/view/viewoftheplate/ViewOfThePlate";
import Home from "./../pages/home/Home";
import Category from "./../pages/view/categoria/Category";
import SearchPage from "../pages/SearchPage/SearchPage";

export const router = createHashRouter([
	{
		path: "/",
		element: <Home />,
	},
	{
		path: "/categoria",
		element: <Category />,
		children: [
			{
				path: "/categoria/:idCategory",
				element: <ItemsCategory />,
				children: [
					{
						path: "/categoria/:idCategory/:idFood",
						element: <ViewOfThePlate />,
					},
				],
			},
		],
	},

	{
		path: "/promociones",
		element: <Promotions />,
	},
	{
		path: "/contacto",
		element: <Contact />,
	},
	{
		path: "/myaccount",
		element: <MyAccount />,
	},
	{
		path: "/login",
		element: <Auth />,
	},
	{
		path: "/signup",
		element: <Auth />,
	},
	{
		path: "/checkout",
		element: <Checkout />,
	},
	{
		path: "/search",
		element: <SearchPage />,
	},
	{
		path: "/thanks",
		element: <Thanks />,
	},
	{
		path: "/loading",
		element: <Loader />,
	},
	{
		path: "/adminCreate",
		element: <AdminCreate />,
	},
]);
