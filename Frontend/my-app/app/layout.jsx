import "./globals.css";

export default function RootLayout({ children }) {
  return (
    <html lang="ru">
        <head>
            <title>Файлообменник</title>
            <meta httpEquiv="X-UA-Compatible" content="IE=edge" />
        </head>
      <body>
          <header>
            <div>
                <h1>Главная</h1>
            </div>
           <div className="profile-button">
               <button>Профиль</button>
           </div>
           </header>
          {children}
          <footer>
              <p>© 2025 свэн-тим</p>
          </footer>
      </body>
    </html>
  );
}
