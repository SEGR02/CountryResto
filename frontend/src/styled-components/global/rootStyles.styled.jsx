import { createGlobalStyle } from "styled-components";

export const GlobalStyle = createGlobalStyle`

    :root{

        --letterscustomizable-7: clamp(0.91rem, calc(0.89rem + 0.1vw), 0.96rem);
        --letterscustomizable-6: clamp(1.09rem, calc(1.05rem + 0.21vw), 1.2rem);
        --letterscustomizable-5: clamp(1.31rem, calc(1.24rem + 0.37vw), 1.5rem);
        --letterscustomizable-4: clamp(1.58rem, calc(1.46rem + 0.59vw), 1.88rem);
        --letterscustomizable-3: clamp(1.89rem, calc(1.71rem + 0.89vw), 2.34rem);
        --letterscustomizable-2: clamp(2.27rem, calc(2.01rem + 1.29vw), 2.93rem);
        --letterscustomizable-1: clamp(2.72rem, calc(2.36rem + 1.83vw), 3.66rem);
        --letterscustomizable-0: clamp(3.27rem, calc(2.75rem + 2.56vw), 4.58rem);

        --background-nav-bar: rgba(0, 0, 0, 1);
        --background-primary: rgba(14, 14, 14, 1);   
        --background-secondary: rgba(45, 45, 45, 1);
        --text-primary: rgba(255, 255, 255, 1);
        --text-secondary: rgba(172, 172, 172, 1);
        --button-primary: rgba(255, 168, 0, 1);
        --button-secondary: rgba(255, 196, 81, 1);
        --button-disabled: rgba(255, 168, 0, 0.4);
        --second: rgba(252, 219, 155, 1);
        --font-family:  'Poppins', sans-serif;
        --hidden: auto;
    }   

    html{
        box-sizing: border-box;
    }

    * , ::after, ::before{
        box-sizing: inherit;
    }

    body{
        margin: 0;
        font-family: var(--font-family);
        background-color: var(--background-primary);
        background-color: var(--background-primary);
        color: var(--text-primary);
        overflow: var(--hidden);
    }

    button{
        background: none;
        border: none;
    }

    ul,li{
        list-style: none;
        margin: 0;
        padding: 0;
    }

    a {
        text-decoration: none;
        color: inherit;
    }
    img{
        max-width: 100%;
        inline-size: auto;
    }
`;
