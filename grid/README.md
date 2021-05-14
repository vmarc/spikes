Commands that were used to create the spike:
```
nvm use 14.17.0
```

```
npx -p @angular/cli@latest ng new grid \
  --inline-style \
  --inline-template \
  --minimal \
  --package-manager yarn \
  --prefix grid \
  --routing \
  --style scss\
  --verbose
```

```
cd grid
yarn add @angular/material
yarn add ag-grid-community
yarn add ag-grid-angular
yarn add --dev eslint
yarn add --dev @typescript-eslint/eslint-plugin eslint-plugin-prettier
yarn add --dev prettier prettier-eslint eslint-config-prettier
```