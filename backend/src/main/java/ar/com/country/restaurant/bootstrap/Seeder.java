package ar.com.country.restaurant.bootstrap;

import ar.com.country.restaurant.dao.entities.*;
import ar.com.country.restaurant.dao.entities.spec.DishCategorySpec;
import ar.com.country.restaurant.dao.entities.spec.DishSpec;
import ar.com.country.restaurant.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

@Component
@RequiredArgsConstructor
@Profile("seeder")
public class Seeder implements CommandLineRunner {
    private final DishService dishService;

    private final DishCategoryService dishCategoryService;

    private final UserService userService;

    private final CommentService commentService;

    private final PaymentMethodService paymentMethodService;

    private final AddressService addressService;

    @Override
    @Transactional
    public void run(String... args) {
        // Payment Methods
        String date1 = "02/05/2025";
        String date2 = "22/09/2024";
        String date3 = "15/07/2028";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        PaymentMethod paymentMethod1 = createPaymentMethod(CardType.CREDIT, "1234567890123456", LocalDate.parse(date1, formatter), "123");

        PaymentMethod paymentMethod2 = createPaymentMethod(CardType.DEBIT, "9234983890123456", LocalDate.parse(date2, formatter), "505");

        PaymentMethod paymentMethod3 = createPaymentMethod(CardType.CREDIT, "4704567890203456", LocalDate.parse(date3, formatter), "814");

        // Direcciones
        Address address1 = createAddress("Av. Corrientes", "1234", "Buenos Aires", "CABA", "Argentina", "C1000AAA");

        Address address2 = createAddress("Av. Santa Fe", "1234", "Buenos Aires", "CABA", "Argentina", "C1000AAA");

        Address address3 = createAddress("Av. Cabildo", "1234", "Buenos Aires", "CABA", "Argentina", "C1000AAA");

        Address address4 = createAddress("Av. Rivadavia", "1234", "Buenos Aires", "CABA", "Argentina", "C1000AAA");

        Address address5 = createAddress("Av. Callao", "1234", "Buenos Aires", "CABA", "Argentina", "C1000AAA");

        Address address6 = createAddress("Av. Pueyrredon", "1234", "Buenos Aires", "CABA", "Argentina", "C1000AAA");


        // Usuarios
        User user1 = createUser("Gaston Gutierrez", "gastongutierrez@gmail.com", "12345678", UserRole.NORMAL);
        User user2 = createUser("Maria Perez", "mariaperez@gmail.com", "12345678", UserRole.NORMAL);
        User user3 = createUser("Anish Giri", "anishgiri@gmail.com", "12345678", UserRole.NORMAL);


        // Categorías
        DishCategory dishCategory1 = new DishCategory();
        dishCategory1.setName("Pizzas");
        dishCategory1.setImage(new DishImage("https://res.cloudinary.com/doxahduh8/image/upload/v1676594881/Diseño_sin_título_80_1_vahbhd.png"));

        DishCategory dishCategory2 = new DishCategory();
        dishCategory2.setName("Pastas");
        dishCategory2.setImage(new DishImage("https://res.cloudinary.com/doxahduh8/image/upload/v1676594879/categoria_pastas_1_uu60cm.png"));

        DishCategory dishCategory3 = new DishCategory();
        dishCategory3.setName("Saludables");
        dishCategory3.setImage(new DishImage("https://res.cloudinary.com/doxahduh8/image/upload/v1676594879/Diseño_sin_título_82_1_sobknf.png"));

        DishCategory dishCategory4 = new DishCategory();
        dishCategory4.setName("Cafeteria");
        dishCategory4.setImage(new DishImage("https://res.cloudinary.com/doxahduh8/image/upload/v1676594930/Diseño_sin_título_89_1_lbau3n.png"));

        DishCategory dishCategory5 = new DishCategory();
        dishCategory5.setName("Postres");
        dishCategory5.setImage(new DishImage("https://res.cloudinary.com/doxahduh8/image/upload/v1676594931/Diseño_sin_título_79_1_yviibk.png"));

        DishCategory dishCategory6 = new DishCategory();
        dishCategory6.setName("Churrasqueria");
        dishCategory6.setImage(new DishImage("https://res.cloudinary.com/doxahduh8/image/upload/v1676594931/Dise%C3%B1o_sin_t%C3%ADtulo_88_1_btb96u.png"));

        DishCategory saveDishCategory1 = dishCategoryService.createDishCategory(new DishCategorySpec(dishCategory1));
        DishCategory saveDishCategory2 = dishCategoryService.createDishCategory(new DishCategorySpec(dishCategory2));
        DishCategory saveDishCategory3 = dishCategoryService.createDishCategory(new DishCategorySpec(dishCategory3));
        DishCategory saveDishCategory4 = dishCategoryService.createDishCategory(new DishCategorySpec(dishCategory4));
        DishCategory saveDishCategory5 = dishCategoryService.createDishCategory(new DishCategorySpec(dishCategory5));
        DishCategory saveDishCategory6 = dishCategoryService.createDishCategory(new DishCategorySpec(dishCategory6));


        // Platillos
        Dish dish_1 = createDish("Combo libra", "Hamburguesa, lechuga, tomate, cebolla morada, pepino, y cheddar + papas", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594926/Dise%C3%B1o_sin_t%C3%ADtulo_-_2023-02-15T183907_1_cbjj8f.png", 3500D, saveDishCategory6, 1, 1);
        DishSpec dish1 = new DishSpec(dish_1, 6L);

        Dish dish_2 = createDish("Tabla I", "Carne salteada, aceituna, salame, queso roquefort, queso caprese, salsa picante", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594925/Dise%C3%B1o_sin_t%C3%ADtulo_90_1_gatcrg.png", 2500D, saveDishCategory6, 1, 1);
        DishSpec dish2 = new DishSpec(dish_2, 6L);

        Dish dish_3 = createDish("Tabla II", "Salame, jamon crudo, queso gruyere, aceitunas mixtas, salsa especial", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594927/Dise%C3%B1o_sin_t%C3%ADtulo_91_1_ioviex.png", 2800D, saveDishCategory6, 1, 1);
        DishSpec dish3 = new DishSpec(dish_3, 6L);

        Dish dish_4 = createDish("Tabla III", "Salsa picante, salsa cheddar, salsa 4 quesos, choclo asado, mix de chorizos, porcion de papas", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594925/Dise%C3%B1o_sin_t%C3%ADtulo_92_1_ijfnos.png", 4800D, saveDishCategory6, 1, 1);
        DishSpec dish4 = new DishSpec(dish_4, 6L);

        Dish dish_5 = createDish("Pizza mixta", "Panceta, choclo, aceitunas negras y champiñon", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594921/pexels-yassir-abbas-12046657_1_xvhuoi.png", 1020D, saveDishCategory6, 1, 1);
        DishSpec dish5 = new DishSpec(dish_5, 1L);

        Dish dish_6 = createDish("Pizza cantimpalo", "Queso muzzarella, cantimpalo y morrón", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594921/Dise%C3%B1o_sin_t%C3%ADtulo_-_2023-02-16T132343_1_nlhtir.png", 1420D, saveDishCategory1, 8, 2);
        DishSpec dish6 = new DishSpec(dish_6, 1L);

        Dish dish_7 = createDish("Pizza de rúcula", "Queso muzzarella, tomate cherry y rúcula", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594917/Dise%C3%B1o_sin_t%C3%ADtulo_-_2023-02-16T131849_1_mq6z3d.png", 1320D, saveDishCategory1, 8, 2);
        DishSpec dish7 = new DishSpec(dish_7, 1L);

        Dish dish_8 = createDish("Pizza de choclo", "Queso muzzarella, salsa de choclo, aceitunas negras y tomate", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594920/choclo_1_1_fkzkn8.png", 1720D, saveDishCategory1, 8, 2);
        DishSpec dish8 = new DishSpec(dish_8, 1L);

        Dish dish_9 = createDish("Pizza de muzzarella", "Queso muzzarella y hojas de albahaca", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594914/Dise%C3%B1o_sin_t%C3%ADtulo_-_2023-02-16T133745_1_rri4o3.png", 1200D, saveDishCategory1, 8, 2);
        DishSpec dish9 = new DishSpec(dish_9, 1L);

        Dish dish_10 = createDish("Pizza de especial", "Queso muzzarella, aceitunas, panceta, champiñon y verdeo", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594913/shourav-sheikh-a66sGfOnnqQ-unsplash_1_fetnur.png", 1800D, saveDishCategory1, 8, 2);
        DishSpec dish10 = new DishSpec(dish_10, 1L);

        Dish dish_11 = createDish("Panchos", "Salsa criolla, tomate, lechuga + 3 aderezos", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594917/Dise%C3%B1o_sin_t%C3%ADtulo_-_2023-02-15T184205_1_uksalm.png", 1890D, saveDishCategory6, 4, 1);
        DishSpec dish11 = new DishSpec(dish_11, 6L);

        Dish dish_12 = createDish("Papas con cheddar", "Bastones de papas, queso cheddar, verdeo y panceta", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594915/Dise%C3%B1o_sin_t%C3%ADtulo_-_2023-02-16T140448_1_ctpp1u.png", 1200D, saveDishCategory6, 10, 2);
        DishSpec dish12 = new DishSpec(dish_12, 6L);

        Dish dish_13 = createDish("Tostado especial", "Pollo, queso muzzarella y verduras salteadas", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594915/Dise%C3%B1o_sin_t%C3%ADtulo_-_2023-02-15T184015_1_zjcsdv.png", 1300D, saveDishCategory6, 1, 1);
        DishSpec dish13 = new DishSpec(dish_13, 6L);

        Dish dish_14 = createDish("Tiramisú", "Bizcocho, cacao, mascarpone y cafe", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594907/Dise%C3%B1o_sin_t%C3%ADtulo_75_1_zjewuh.png", 2850D, saveDishCategory5, 1, 1);
        DishSpec dish14 = new DishSpec(dish_14, 5L);

        Dish dish_15 = createDish("Lemon pie", "Mouse de limon y merengue", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594906/t_con_fruto_2_1_lle7wp.png", 1150D, saveDishCategory5, 1, 4);
        DishSpec dish15 = new DishSpec(dish_15, 5L);

        Dish dish_16 = createDish("Brownie", "Baño de chocolate + bombón Ferrero", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594906/vinitha-v-E_uxJrOKw6I-unsplash_1_fgp02z.png", 750D, saveDishCategory5, 1, 1);
        DishSpec dish16 = new DishSpec(dish_16, 5L);

        Dish dish_17 = createDish("Pancakes de fresas", "Pancakes con frutos rojos y banana", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594903/parvez-azarqaderi-f2LR0IIRd5s-unsplash_1_y3mcyw.png", 650D, saveDishCategory5, 1, 1);
        DishSpec dish17 = new DishSpec(dish_17, 5L);

        Dish dish_18 = createDish("Pastafrola", "Mixta de batata y membrillo", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594910/t_con_fruto_1_1_lvafpw.png", 1350D, saveDishCategory5, 1, 1);
        DishSpec dish18 = new DishSpec(dish_18, 5L);

        Dish dish_19 = createDish("Helado", "Gusto a elección + frutos rojos", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594906/t_con_fruto_3_1_yecdq7.png", 900D, saveDishCategory5, 1, 1);
        DishSpec dish19 = new DishSpec(dish_19, 5L);

        Dish dish_20 = createDish("Ensalada de frutas", "Frutos rojos, naranja, durazno, uvas y kiwi", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594903/t_con_fruto_1_m6zziv.png", 750D, saveDishCategory4, 1, 1);
        DishSpec dish20 = new DishSpec(dish_20, 4L); //

        Dish dish_21 = createDish("Desayuno especial", "Medialunas rellenas", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594895/Dise%C3%B1o_sin_t%C3%ADtulo_97_1_jjtz6f.png", 1050D, saveDishCategory4, 1, 1);
        DishSpec dish21 = new DishSpec(dish_21, 4L);

        Dish dish_22 = createDish("Waffles de fresas", "Waffle, fresas y moras", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594901/Dise%C3%B1o_sin_t%C3%ADtulo_74_1_xqixti.png", 650D, saveDishCategory4, 5, 1);
        DishSpec dish22 = new DishSpec(dish_22, 4L);

        Dish dish_23 = createDish("Desayuno de campo", "Tostadas de campo", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594899/Dise%C3%B1o_sin_t%C3%ADtulo_96_1_h7s2hy.png", 800D, saveDishCategory4, 1, 1);
        DishSpec dish23 = new DishSpec(dish_23, 4L);

        Dish dish_24 = createDish("Desayuno clásico", "Medialunas integrales", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594899/Dise%C3%B1o_sin_t%C3%ADtulo_98_1_baphfy.png", 850D, saveDishCategory4, 1, 1);
        DishSpec dish24 = new DishSpec(dish_24, 4L);

        Dish dish_25 = createDish("Mix primavera", "Yogurt natural, frutos rojos, avenas, banana y durazno", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594902/tetiana-bykovets-T4Q2NX1GP4w-unsplash_1_zvce4h.png", 850D, saveDishCategory4, 1, 1);
        DishSpec dish25 = new DishSpec(dish_25, 4L);

        Dish dish_26 = createDish("Ensalada primavera", "Papas, palta, pepino, choclo, garbanzos y zanahoria asada", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594894/Dise%C3%B1o_sin_t%C3%ADtulo_77_1_kgbwpm.png", 1050D, saveDishCategory3, 1, 1);
        DishSpec dish26 = new DishSpec(dish_26, 3L);

        Dish dish_27 = createDish("Ensalada mixta", "Huevo, rúcula, pollo, tomate, zanahoria salteada", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594894/Ellipse_12_k7tqxn.png", 840D, saveDishCategory3, 1, 1);
        DishSpec dish27 = new DishSpec(dish_27, 3L);

        Dish dish_28 = createDish("Ensalada verano", "Mix de vegetales + carne", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594901/tania-melnyczuk-xeTv9N2FjXA-unsplash_1_dlasor.png", 900D, saveDishCategory3, 1, 1);
        DishSpec dish28 = new DishSpec(dish_28, 3L);

        Dish dish_29 = createDish("Ensalada griega", "Mix de vegetales + pollo", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594895/anh-nguyen-kcA-c3f_3FE-unsplash_1_upsvn9.png", 800D, saveDishCategory3, 1, 1);
        DishSpec dish29 = new DishSpec(dish_29, 3L);

        Dish dish_30 = createDish("Sopa de espinaca", "Espinaca, acelga, papas y huevo", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594889/t_con_fruto_5_1_xlwoyn.png", 500D, saveDishCategory3, 1, 1);
        DishSpec dish30 = new DishSpec(dish_30, 3L);

        Dish dish_31 = createDish("Sopa clásica", "Mix de vegetales", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594888/t_con_fruto_4_1_ng44of.png", 500D, saveDishCategory3, 1, 1);
        DishSpec dish31 = new DishSpec(dish_31, 3L);

        Dish dish_32 = createDish("Tallarines", "Tomate cherry, albahaca y aceitunas negras", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594883/plato_pasta_1_x6nev6.png", 950D, saveDishCategory2, 1, 1);
        DishSpec dish32 = new DishSpec(dish_32, 2L);

        Dish dish_33 = createDish("Ñoquis", "Salsa de tomate, queso crema y albahaca", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594883/nathalie-klippert-c8anfbnfPRo-unsplash_1_qqedns.png", 950D, saveDishCategory2, 1, 1);
        DishSpec dish33 = new DishSpec(dish_33, 2L);

        Dish dish_34 = createDish("Ravioles", "Salsa de calabaza y perejil", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594879/nicolas-cuestas-xc-067-UsOQ-unsplash_1_txttcz.png", 950D, saveDishCategory2, 1, 1);
        DishSpec dish34 = new DishSpec(dish_34, 2L);

        Dish dish_35 = createDish("Ravioles", "Salsa de calabaza y perejil", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594879/nicolas-cuestas-xc-067-UsOQ-unsplash_1_txttcz.png", 950D, saveDishCategory2, 1, 1);
        DishSpec dish35 = new DishSpec(dish_35, 2L);

        Dish dish_36 = createDish("Mostaccioli", "Salsa de tomate, aceitunas y albahaca", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594884/t_con_fruto_6_1_sisjhr.png", 950D, saveDishCategory2, 1, 1);
        DishSpec dish36 = new DishSpec(dish_36, 2L);

        Dish dish_37 = createDish("Carne asada", "Carne asada, tomate y aceitunas negras", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594891/Dise%C3%B1o_sin_t%C3%ADtulo_93_1_sscdbu.png", 1020D, saveDishCategory6, 1, 1);
        DishSpec dish37 = new DishSpec(dish_37, 6L);

        Dish dish_38 = createDish("Carne española", "Carne y salsa picante", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594875/Dise%C3%B1o_sin_t%C3%ADtulo_94_1_bkekzx.png", 1020D, saveDishCategory6, 1, 1);
        DishSpec dish38 = new DishSpec(dish_38, 6L);

        Dish dish_39 = createDish("Matambre", "Verduras salteadas", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594871/t_con_fruto_8_1_fgl9nk.png", 1050D, saveDishCategory6, 1, 1);
        DishSpec dish39 = new DishSpec(dish_39, 6L);

        Dish dish_40 = createDish("Matambre", "Con verduras y papas fritas", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594871/t_con_fruto_10_1_ckxmms.png", 1050D, saveDishCategory6, 1, 1);
        DishSpec dish40 = new DishSpec(dish_40, 6L);

        Dish dish_41 = createDish("Hamburguesa", "Queso cheddar, cebolla salteada y muzzarella", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594872/t_con_fruto_11_1_gzzwzh.png", 1250D, saveDishCategory6, 1, 1);
        DishSpec dish41 = new DishSpec(dish_41, 6L);

        Dish dish_42 = createDish("Arrollado de carne", "Verduras salteadas", "https://res.cloudinary.com/doxahduh8/image/upload/v1676594871/t_con_fruto_13_1_pesyie.png", 1250D, saveDishCategory6, 1, 1);
        DishSpec dish42 = new DishSpec(dish_42, 6L);

        Dish dish_43 = createDish("Mostaccioli", "Salsa blanca con zapallo", "https://res.cloudinary.com/doxahduh8/image/upload/v1677692260/t_con_fruto_7_1_scfo7e.png", 950D, saveDishCategory2, 1, 1);
        DishSpec dish43 = new DishSpec(dish_43, 2L);

        // Comentarios
        Comment comment1 = new Comment();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2022, Calendar.JULY, 1);
        comment1.setDish(dish_1);
        comment1.setUser(user1);
        comment1.setContent("Muy ricas hamburguesas, sin dudas volveré a pedir");
        comment1.setCreatedAt(calendar1);

        Comment comment2 = new Comment();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2022, Calendar.SEPTEMBER, 18);
        comment2.setDish(dish_2);
        comment2.setUser(user2);
        comment2.setContent("Espectacular la comida, el servicio fue muy bueno");

        Comment comment3 = new Comment();
        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(2023, Calendar.JANUARY, 22);
        comment3.setDish(dish_3);
        comment3.setUser(user3);
        comment3.setContent("Todo excelente, muy recomendable");

        // Promociones
        Promotion promotion1 = createPromotion(20D);
        dish_1.setPromotion(promotion1);

        Promotion promotion2 = createPromotion(20D);
        dish_2.setPromotion(promotion2);

        Promotion promotion3 = createPromotion(30D);
        dish_3.setPromotion(promotion3);

        Promotion promotion4 = createPromotion(50D);
        dish_4.setPromotion(promotion4);

        // Promotion promotion5 = createPromotion(50D);
        // dish_5.setPromotion(promotion5);

        // Uso de services
        DishSpec[] dishes = {dish1, dish2, dish3, dish4, dish5, dish6, dish7, dish8, dish9, dish10, dish11, dish12, dish13, dish14, dish15, dish16, dish17, dish18, dish19, dish20, dish21, dish22, dish23, dish24, dish25, dish26, dish27, dish28, dish29, dish30, dish31, dish32, dish33, dish34, dish35, dish36, dish37, dish38, dish39, dish40, dish41, dish42, dish43};
        for(DishSpec dish : dishes) {
            dishService.createDish(dish);
        }

        userService.createUser(user1);
        userService.createUser(user2);
        userService.createUser(user3);

        addressService.addAddressToUser(user1.getId(), address1);
        addressService.addAddressToUser(user2.getId(), address2);
        addressService.addAddressToUser(user2.getId(), address3);
        addressService.addAddressToUser(user3.getId(), address4);
        addressService.addAddressToUser(user3.getId(), address5);
        addressService.addAddressToUser(user3.getId(), address6);

        paymentMethod1.setHolder(user1.getName());
        paymentMethodService.addPaymentMethodToUser(user1.getId(), paymentMethod1);
        paymentMethod2.setHolder(user2.getName());
        paymentMethodService.addPaymentMethodToUser(user2.getId(), paymentMethod2);
        paymentMethod3.setHolder(user3.getName());
        paymentMethodService.addPaymentMethodToUser(user3.getId(), paymentMethod3);

        commentService.addCommentToDish(user1.getId(), dish_1.getId(), comment1);
        commentService.addCommentToDish(user2.getId(), dish_2.getId(), comment2);
        commentService.addCommentToDish(user3.getId(), dish_3.getId(), comment3);
    }

    public PaymentMethod createPaymentMethod(CardType cardType, String cardNumber, LocalDate expirationDate, String cvv) {
        return PaymentMethod.builder()
                .type(cardType)
                .number(cardNumber)
                .expirationDate(expirationDate)
                .cvv(cvv)
                .build();
    }

    public Address createAddress(String street, String number, String city, String state, String country, String zipCode) {
        return Address.builder()
                .street(street)
                .number(number)
                .city(city)
                .state(state)
                .country(country)
                .zipCode(zipCode)
                .build();
    }

    public User createUser(String name, String email, String password, UserRole role) {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .role(role)
                .build();
    }


    public Dish createDish(String name, String description, String image, Double price, DishCategory category, Integer portionPerUnit, Integer people) {
        Dish dish = new Dish();
        dish.setName(name);
        dish.setDescription(description);
        dish.setImage(new DishImage(image));
        dish.setPrice(price);
        dish.setCategory(category);
        dish.setPortionPerUnit(portionPerUnit);
        dish.setPeople(people);
        return dish;
    }

    public Promotion createPromotion(Double discountPercentage) {
        Promotion promotion = new Promotion();
        promotion.setDiscountPercentage(discountPercentage);
        return promotion;
    }
}
