import "./globals.css";

export default function RootLayout({ children }) {
  return (
    <html lang="en">
        <head>
            <meta charSet="UTF-8" />
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
