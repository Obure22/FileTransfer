import "./globals.css";

export default function RootLayout({ children }) {
  return (
    <html lang="ru">
        <head>
            <title>Файлообменник</title>
            <meta httpEquiv="X-UA-Compatible" content="IE=edge" />
        </head>
      <body>
          <header className={"header"}>
            <div className={"header-div"}>
                <h1 className={"header-div-text"}>Главная</h1>
            </div>
           <div className={"header-profile-div"}>
               <button className={"header-profile-div-button"}>Профиль</button>
           </div>
           </header>
          {children}
          <footer className={"footer"}>
              <p className={"footer-text"}>© 2025 свэн-тим</p>
          </footer>
      </body>
    </html>
  );
}