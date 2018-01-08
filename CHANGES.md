# Changes

## 0.2.0-alpha1 (2018-01-08)

[source](https://github.com/gadfly361/re-surface/tree/v0.2.0-alpha1)

- don't require height on header, navbar, and footer
- dont' require key on header-dropdown, navbar-dropdown, modal, and modal-fullscreen
  - rationale: if you want to use multiple dropdowns or modals on a page, then removing the key requirement makes this process easier
- remove transition on the way out for header-dropdown, navbar-dropdown, modal, and modal-fullscreen
- add `default-comp` to header-dropdown, navbar-dropdown, modal and modal-fullscreen
- add mostly transparent dimmer option to header-dropdown and navbar-dropdown

## 0.1.0 (2017-12-12)

[source](https://github.com/gadfly361/re-surface/tree/v0.1.0)

- don't require height on modal

## 0.1.0-alpha5 (2017-11-07)

[source](https://github.com/gadfly361/re-surface/tree/v0.1.0-alpha5)

- fix bug where modal had two vertical scrollbars [#4](https://github.com/gadfly361/re-surface/issues/4)
- add transition on width to sidebars [#6](https://github.com/gadfly361/re-surface/issues/6)
- fix bug where header-dropdown / navbar-dropdown always had white background [#7](https://github.com/gadfly361/re-surface/issues/7)

## 0.1.0-alpha4 (2017-10-30)

[source](https://github.com/gadfly361/re-surface/tree/v0.1.0-alpha4)

- add `full-width?` to header-dropdown and sidebar-dropdown [#3](https://github.com/gadfly361/re-surface/issues/3)
- fix bug where sidebar wouldn't show content if larger than height of screen [#2](https://github.com/gadfly361/re-surface/issues/2)

## 0.1.0-alpha3 (2017-10-25)

[source](https://github.com/gadfly361/re-surface/tree/v0.1.0-alpha3)

- fix bug where `spec?` always returned true, [#1](https://github.com/gadfly361/re-surface/issues/1)

## 0.1.0-alpha2 (2017-10-25)

[source](https://github.com/gadfly361/re-surface/tree/v0.1.0-alpha2)

- add `header-dropdown`
- add `navbar-dropdown`
- fix spacing bug when navbar is fixed but header isn't

## 0.1.0-alpha (2017-10-25)

[source](https://github.com/gadfly361/re-surface/tree/v0.1.0-alpha)

- initial release
