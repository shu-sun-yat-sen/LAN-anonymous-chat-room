// module.exports = {
//   presets: [
//     '@vue/cli-plugin-babel/preset',{targets: {node: 'current'}}
//   ]
// }
module.exports = {
  env: {
    test: {
      presets: [
        [
          "@babel/preset-env",
          {
            targets: { node: "current" },
          },
        ],
      ],
    },
  },
};
