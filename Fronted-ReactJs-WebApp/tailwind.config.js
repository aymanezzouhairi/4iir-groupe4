/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
      extend: {
        fontFamily: {
          custom: ['Roboto', 'sans-serif'], // Replace 'Roboto' with your chosen custom font
        },
        gradientColorStops: theme => ({
          ...theme('colors'),
          'custom-start': '#15165E', // Custom gradient start color
          'custom-end': '#37C542', // Custom gradient end color
        }),
    },
  },
  plugins: [],

  
}