# Re-surface

This library is an attempt to provide structure to
a [reagent](https://github.com/reagent-project/reagent) application at
the **page** level (which we will be calling a `surface`).

[Demo](http://rawgit.com/gadfly361/re-surface/master/demo/index.html)

[Source](https://github.com/gadfly361/re-surface/tree/master/src/demo/re_surface)

To use re-surface, add the following to the `:dependencies` vector in your project.clj file:

```clojure
[re-surface "0.1.0-alpha3"]
```

**Note: This is very much a work in progress and subject to sweeping changes.**

# The problem

> Should this be position fixed or absolute?
> If this is position fixed, then do I need to add margin to that?
> Why isn't the z-index working ... shouldn't this be higher than that?

When I first start an application, I am excited and eager to get
going. Generally, I just start barreling forward writing clojurescript
without much concern for writing quality CSS. 

In particular, the CSS regarding the page structure is a mess. It
isn't until I am asked to add a sidebar or to make a sticky header
that I even give my CSS a real thought. However, by that time, I have
already been using some haphazard convention, and, well ...  since
there is already a lot of momentum, I just hack together something
that works. Something that will specifically add that sidebar or that
sticky header, but not something that I am proud of. You know,
when you add a lot of control flow logic or disaggregated state or
when you just close your eyes and add new classes to the bottom of an
ever-growing stylesheet and hope that no one judges you.

# Re-surface's solution

Re-surface exposes a single reagent component, `surface`, which
provides a reasonable page structure for 80% of applications.

## surface component

A `surface` takes a hash-map of the following:

| key                | type               | default     | required? |
|--------------------|--------------------|-------------|-----------|
| :app-state         | reagent.core/atom  |             | no (1)    |
| :component-registry| component-registry |             | **yes**   |
| :surface-config    | surface-config     |             | no        |
| :surface-key       | keyword            |             | **yes**   |
| :surface-registry  | surface-registry   |             | **yes**   |


(1) The `:app-state` value will get passed to the components of a
surface. This is useful if you are using vanilla reagent. However, if you are
using re-frame, you can go ahead and ignore this.

## component-registry

A component-registry is a hash-map of the following keys:

- `:header`
- `:header-dropdown`
- `:navbar`
- `:navbar-dropdown`
- `:body`
- `:footer`
- `:dimmer`
- `:sidebar-left`
- `:sidebar-right`
- `:modal`
- `:modal-fullscreen`

The value of each of these is a hash-map where:
- the key is the `:key` used in the `surface-registry`, and
- the value is a reagent-component that takes a single parameter, `app-state`.

An example component-registry could look like this:

```clojure
{:header {:default (fn [app-state]
                     [:h1 "Header"])}

 :body {:home (fn [app-state]
                [:div
                 [:h1 "This is the home page"]])

        :about (fn [app-state]
                 [:div
                  [:h1 "This is the about page"]])}}
```

## surface-registry

An example surface-registry could look like this:

```clojure
{:home-page
 {:header {:key    :default
           :height 100}

  :body {:key :home}}

 :about-page
 {:header {:key    :default
           :height 100}

  :body {:key :about}}}
```

A surface-registry is a hash-map.
- The keys are used by `surface-key` to lookup which surface to render.
- The values are a hash-map of the following:

### :header / :navbar / :footer

`:header`, `:navbar`, and `:footer` take a hash-map of the following:

| key          | type               | default     | required? |
|--------------|--------------------|-------------|-----------|
| :background-color | string        | "white"     | no        |
| :fixed?      | boolean            | false       | no        |
| :height      | int                |             | **yes**   |
| :key         | keyword            |             | **yes**   |

- `:key` is used to lookup a component from the component-registry

### :header-dropdown / :navbar-dropdown

`:header-dropdown` and `:navbar-dropdown` take a hash-map of the following:

| key          | type               | default     | required? |
|--------------|--------------------|-------------|-----------|
| :active?     | boolean            | false       | no        |
| :background-color | string        | "white"     | no        |
| :bottom      | int                |             | no        |
| :height      | int                |             | no        |
| :key         | keyword            |             | **yes**   |
| :left        | int                |             | no        |
| :right       | int                |             | no        |
| :top         | int                |             | no        |
| :width       | int                |             | no        |

### :body

`:body` takes a hash-map of the following:

| key          | type               | default     | required? |
|--------------|--------------------|-------------|-----------|
| :background-color | string        | "white"     | no        |
| :key         | keyword            |             | **yes**   |


### :dimmer

`:dimmer` takes a hash-map of the following:

| key          | type               | default     | required? |
|--------------|--------------------|-------------|-----------|
| :key         | keyword            |             | **yes**   |

### :sidebar-left / :sidebar-right

`:sidebar-left` and `:sidebar-right` take a hash-map of the following:

| key          | type               | default     | required? |
|--------------|--------------------|-------------|-----------|
| :active?     | boolean            | false       | no        |
| :background-color | string        | "white"     | no        |
| :fixed?      | boolean            | false       | no        |
| :key         | keyword            |             | **yes**   |
| :width       | int                |             | **yes**   |


### :modal

`:modal` takes a hash-map of the following:

| key          | type               | default     | required?  |
|--------------|--------------------|-------------|------------|
| :active?     | boolean            | false       | no         |
| :background-color | string        | "white"     | no         |
| :height      | int                |             | **yes**    |
| :key         | keyword            |             | **yes**    |
| :width       | int                |             | **yes**    |

### :modal-fullscreen

`:modal-fullscreen` takes a hash-map of the following:

| key          | type               | default     | required?  |
|--------------|--------------------|-------------|------------|
| :active?     | boolean            | false       | no         |
| :background-color | string        | "white"     | no         |
| :key         | keyword            |             | **yes**    |


## surface-config

`:surface-config` takes a hash-map of the following:

| key              | type                           | default     | required?  |
|------------------|--------------------------------|-------------|------------|
| :debug?          | boolean                        | false       | no         |
| :spec?           | boolean                        | true        | no         |
| :style-component | (fn [opts] [:style ...])       |             | no         |
| :z-indicies      | z-indicies                     |             | no         |

- `:debug?` can be used if you'd like to see a background color on each of the elements during development. This was used when creating the demo.
- `:spec?` can be used to turn on/off cljs.spec assertions.
- `:style-component` really **shouldn't** be used. This is an escape-hatch to override the CSS provided by re-surface if you absolutely needed to.
- `:z-indicies` really **shouldn't** be used. This is an escape-hatch to override the z-indicies used for each element.

# What's next?

The following things are on my mind:

- notifications
- snackbar / toast

# Questions

If you have questions, I can usually be found hanging out in
the [clojurians](http://clojurians.net/) #reagent slack channel (my
handle is `@gadfly361`).

# License

```
The MIT License (MIT)

Copyright © 2017 Matthew Jaoudi

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
