import { useDispatch } from "react-redux";
import { openMenu } from "../redux/slice/cart.slice";
import { TabbarStyled } from "../styled-components";
import menuTabbarIcon from "/assets/icons/menu-tabbar.svg";

const Tabbar = () => {
	const dispatch = useDispatch();

	return (
		<TabbarStyled>
			<img
				onClick={() => dispatch(openMenu(true))}
				src={menuTabbarIcon}
				alt="menu icon"
			/>
		</TabbarStyled>
	);
};

export default Tabbar;
